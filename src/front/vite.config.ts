import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import autoprefixer from 'autoprefixer'
import { createHtmlPlugin } from 'vite-plugin-html'
import { visualizer } from 'rollup-plugin-visualizer'


//const basePath = process.env.NODE_ENV !== 'production' || process.env.DISABLE_BASE_PATH === 'true' ? '/' : '/__km_base__/'
const basePath = '/'

// https://vitejs.dev/config/
export default defineConfig({
  base: basePath,
  resolve: {
    alias: [
      {
        find: '@',
        replacement: path.resolve(__dirname, 'src'),
      },
      {
        find: 'config',
        replacement: path.resolve(__dirname, 'src/config'),
      },
    ],
  },
  plugins: [
    vue(),
    createHtmlPlugin({
      minify: false,
      inject: {
        data: {
          basePath,
        },
      },
    }),
    // See: https://github.com/vdesjs/vite-plugin-monaco-editor/issues/21
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    visualizer({
      filename: path.resolve(__dirname, 'bundle-analyzer/stats-treemap.html'),
      template: 'treemap', // sunburst|treemap|network
      gzipSize: true,
      brotliSize: true,
    }),
  ],
  server: {
    port: 8080,
    proxy: {
      '/api': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
        secure: false,
      },
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        api: 'modern',
        additionalData: '@use "@kong/design-tokens/tokens/scss/variables" as *;',
      },
    },
    postcss: {
      plugins: [
        // @ts-ignore
        autoprefixer,
      ],
    },
  },
})
