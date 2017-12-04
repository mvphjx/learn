import Vue from 'vue'
import Router from 'vue-router'
import Videos from '@/components/video/list2'
//import HelloWorld from '@/components/HelloWorld'
Vue.use(Router)

export default new Router({
  routes: [
    {path: '/videos', component: Videos, name: 'Videos'}
  ]
})
