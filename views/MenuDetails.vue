<template>
  <div class="page">
    <div class="header">
      <div class="page-title">{{ $route.params.itemName }}</div>
      <order-cart />
    </div>
    <pizza-details v-bind:pizzaDetails="thisPizzaDetails()" />
  </div>
</template>

<script>
import PizzaService from "../services/PizzaService.js";
import PizzaDetails from "@/components/PizzaDetails.vue";
import OrderCart from "@/components/OrderCart.vue";

export default {
  name: "menu-details-view",
  components: {
    PizzaDetails,
    OrderCart,
  },
  methods: {
    thisPizzaDetails() {
      return this.$store.state.menu.find((item) => {
        if (item.name === this.$route.params.itemName) {
          return item;
        }
      });
    },
  },
  created() {
    PizzaService.getMenu().then((response) => {
      this.$store.commit("SET_PIZZA_MENU", response.data);
      const menuItem = this.$store.state.menu.filter(
        (item) => item.name === this.$route.params.itemName
      );

      this.order.name = menuItem.name;
      this.order.price = menuItem.price;
    });
  },
};
</script>

<style scoped>
/* .page {
  background-color: rgba(255, 255, 255,0.3);
} */
.header {
  display: flex;
  justify-content: space-between;
  padding: 0 5px;
  background-color: rgba(255, 255, 255,0.3);
  color: red;
  height: auto;
}
.item-container {
    display: flex;
    justify-content:center;
}
</style>