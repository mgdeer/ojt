import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
//PrimeVue
import PrimeVue from 'primevue/config';
import "primevue/resources/themes/saga-blue/theme.css"; //theme
import "primevue/resources/primevue.min.css"; //core CSS
import "primeicons/primeicons.css"; //icons
import TabMenu from 'primevue/tabmenu';

createApp(App).use(router)
    .use(PrimeVue).component('TabMenu', TabMenu).mount('#app')
