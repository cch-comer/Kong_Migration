Kong_gui based on https://github.com/Kong/kong-manager.git

# frontend: Vue.js
# backend: Spring boot

***

# needs
1. node Version : v20.18.0
2. JDK 18

***

## Run once when cloned
1. cd src/front
2. npm install -g pnpm
3. pnpm install

***

# How to Use

## 1. Change Configuration
1-1. src/main/resources/application.properties
- PostgreSQL Info,ElasticSearch Info, port

1-2. src/front/vite.config.ts
- server.port

1-3. src/front/src/config.ts
- ADMIN_API_IP(), ADMIN_API_PORT()


## 2. How to Run Server

### 2-1. Run Frontend
- cd src/front
- pnpm serve

### 2-2. Run Backend
- cd src/front
- pnpm build
- Run server