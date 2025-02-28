/*eslint-disable*/
import { useRouter } from 'vue-router'

export function Routes() {
    const router = useRouter()

    const goRoutes = (name) => {
        router.push({name: name})
    }

    const goRoutesData = (name, param) => {
        router.push({ name: name, params: param })
    }

    return { goRoutes, goRoutesData }
}