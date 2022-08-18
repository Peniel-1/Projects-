<template>
    <div>
        <h2 class="title">Menu</h2>
        <form>
            <!-- <router-link v-if="!isCartEmpty" v-bind:to="{ name: 'checkout' }">Checkout</router-link> -->
            <order-cart v-if="!isLoggedIn" />
            <div class="container1">

          
            <pizza-menu />
            <appetizer-menu />
              </div>
              <div class="container2">

             
            <salad-menu />
            <beverage-menu />
             </div>

        </form>
        </div>
</template>

<script>
  import PizzaService from '@/services/PizzaService.js';
  import PizzaMenu from '@/components/PizzaMenu.vue';
import OrderCart from '../components/OrderCart.vue';
import AppetizerMenu from '../components/AppetizerMenu.vue';
import SaladMenu from '../components/SaladMenu.vue';
import BeverageMenu from '../components/BeverageMenu.vue';

  export default {
    components: { 
        PizzaMenu,
        OrderCart,
        AppetizerMenu,
        SaladMenu,
        BeverageMenu
    },
    name: "Home",
    computed: {
      isCartEmpty() {
        return this.$store.state.order.items.length === 0;
      },
      isLoggedIn() {
        return !this.$store.state.user.authorities === undefined;
      }
    },
    created() {
      PizzaService.getMenu().then(response => {
          this.$store.commit("SET_PIZZA_MENU", response.data);
        });
    },
  };
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;700&display=swap');
h1{
  color:white;
  
}
h2{
  color:cornsilk;
  text-shadow: 2px 2px black;
  font-family: 'Dancing Script', cursive;
  font-size: 6em;
}
p{
  color:white;
}
.menu {
  color:white;

}

#title {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: bisque;
}
.container1{
  display: flex ;
 justify-content: space-around;
flex-grow: 1;


}
.container2{
  display: flex;
  justify-content: space-around;
  flex-grow: 1;
}

</style>