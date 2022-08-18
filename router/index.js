import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';
import Login from '@/views/Login.vue';
import Logout from '@/views/Logout.vue';
import Register from '@/views/Register.vue';
import MenuDetails from '@/views/MenuDetails.vue';
import Account from '@/views/Account.vue'
import Checkout from '@/views/Checkout.vue'
import Menu from '@/views/Menu.vue'
import Admin from '@/views/Admin.vue'
import AboutUs from '@/views/AboutUs.vue'
import GoogleMap from '@/views/GoogleMap.vue'


Vue.use(VueRouter);

const routes = [
    {
      path: "/not-found",
      name: "notFound",
      component: NotFound
    },
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout
    },
    {
      path: "/register",
      name: "register",
      component: Register
    },
    {
      path: "/menu",
      name: "menu",
      component: Menu
    },
    {
      path: "/menu/:itemName",
      name: "menu-details",
      component: MenuDetails
    },
    {
      path: "/account",
      name: "account",
      component: Account
    },
    {
      path: "/checkout",
      name: "checkout",
      component: Checkout
    },
    {
      path: "/admin",
      name: "admin",
      component: Admin
    },
    {
    path: "/aboutus",
    name: "about-us",
    component: AboutUs
  },
   {
   path: "/googlemap",
   name: "google-map",
   component: GoogleMap
  },
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  });

export default router;