<template>
  <div>
    Order #: {{ order.order_id }}<br />
    Order Type: {{ order.order_type_name }} <br />
    User Id: {{ order.order_user_name }}<br />
    Order Status: {{ order.order_status_name }}
    <button v-on:click.prevent="updateStatus" v-if="!isCustomer()">Update Status</button>
    <button v-on:click.prevent="cancelOrder">Cancel</button>
  </div>
</template>
<script>
import OrderService from "../services/OrderService";
export default {
  props: {
    order: Object,
  },

  methods: {
    updateStatus() {
      if (this.order.order_status_id === 1) {
        OrderService.updateOrderStatus(3, this.order.order_id);
      } else if (this.order.order_status_id === 3 && this.order.order_type_id === 2) { 
        OrderService.updateOrderStatus(4, this.order.order_id);
      } else if (this.order.order_status_id === 4 || this.order.order_status_id === 3) {
        OrderService.updateOrderStatus(5, this.order.order_id);
      }
    },

    cancelOrder() {
      OrderService.cancelOrder(this.order.order_id);
    },
    isCustomer() {
      if(this.$store.state.user.authorities === undefined) {
        return false;
      }

      return this.$store.state.user.authorities.find( b => b.name == "ROLE_USER") != null;
    },
  },
};
</script>
<style scoped>
div {
  margin: 3px;
  background-color: rgb(238, 211, 199);
  border-radius: 5px;
}
</style>