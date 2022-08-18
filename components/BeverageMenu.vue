<template>
    <div class="pizza-container" v-on:submit.prevent="orderPizza()">
      <h3 class="title">Beverages</h3>
      <pizza-card v-bind:pizza="pizza" v-for="pizza in availableMenu(4)" v-bind:key="pizza.name" />
    </div>
</template>

<script>
import PizzaCard from './PizzaCard.vue';
// import OrderCart from './OrderCart.vue';

export default {
  name: "PizzaMenu",
  data() {
    return {
      order: {}
    };
  },
  components: {
    PizzaCard,
    // OrderCart
  },
  computed: {
    isLoggedIn() {
      return !this.$store.state.user.authorities === undefined;
    }
  },
  methods: {
    availableMenu(item_type_id) {
      return this.$store.state.menu.filter( item => {
        if(item.is_available && item.item_type_id === item_type_id) {
          return item;
        }
      });
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Rubik+Distressed&display=swap');
.title {
    font-family: 'Rubik Distressed', cursive;
    font-size: 2em;
    color:blanchedalmond;
    text-shadow: 2px 2px black;
    text-align: center;
    
}
.pizza-container {
  margin: 2px;
  min-height: 100vh;
  border-style:ridge;
  border-width: 5px;
  border-color: goldenrod;
   margin: 30px 0px 30px 30px;  
    padding: 70px; 
    background-image: linear-gradient(to right, #290c0c, #632b2b, #3e2424)
 }
</style>
