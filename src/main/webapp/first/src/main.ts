import { createApp } from 'vue'
import App from './App.vue'
import router from './router'



import "./style.scss"
//import "./assets/style.css"

//import "bootstrap/scss/bootstrap.scss"
//import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

const app = createApp(App)

app.use(router)

app.mount('#app')
