<template>
    <div>
        <h3>Show Order</h3>
        <purchased-item-details v-for="item in order.items" v-bind:key="item.purchased_item_id" v-bind:item="item" />
    </div>
</template>

<script>
import PurchasedItemDetails from './PurchasedItemDetails.vue'
export default {
  name: "show-order",
  components: { PurchasedItemDetails },
  props: { order: Object }
    
}
</script>

<style scoped>
.cart-item {
  background-color: rgb(238, 211, 199);
  margin: 2px;
  border-radius: 5px;
}
</style>