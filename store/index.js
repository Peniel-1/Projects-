import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    menu: {},
    order: {
      order_user_id: '',
      order_type_id: 1,
      order_status_id: 1,
      total: 0,
      payment_type: 'Cash',
      items: []
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user.name = user.name;
      state.user.id = user.id;
      state.user.authorities = user.authorities;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_PIZZA_MENU(state, menu) {
      state.menu = menu;
    },
    ADD_TO_ORDER(state, item) {
      state.order.items.push(item);
      state.order.total = Math.round((state.order.total + item.price + Number.EPSILON) * 100) / 100;
    },
    SET_USER_ON_ORDER(state) {
      state.order.order_user_id = state.user.id;
    },
    RESET_ORDER(state) {
      state.order = {  
        order_user_id: state.user.id,
        order_type_id: 1,
        order_status_id: 1,
        total: 0,
        payment_type: 'Cash',
        items: []
      };
    },
    SET_DELIVERY_TYPE(state, value){
      state.order.order_type_id = value;
    },
    SET_PAYMENT_TYPE(state, value){
      state.order.payment_type = value;
    },
    
  }
})
