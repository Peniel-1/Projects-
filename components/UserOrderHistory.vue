<template>
    <div>
        <h2>Order History</h2>
        <button @click="toggle = !toggle">Show Order History</button>
        <div v-if="toggle">
        <orderDetails v-for="order in orderHistory" v-bind:key="order.order_id" v-bind:order="order"/>
        </div>
    </div>
</template>

<script>
import OrderService from '../services/OrderService'
import OrderDetails from './OrderDetails.vue'
export default {
    data(){
        return{
            orderHistory:[],
            toggle: false
        }
    },
    components: { OrderDetails },
    name: "user-order-history",
    created(){
        OrderService.listCustomerOrders(this.$store.state.user.id).then(b =>{
            this.orderHistory=b.data;
        });
    },
    methods: {

    }
}
</script>

<style scoped>
h2 {
    color: black;
    font-size: 2.5em;
     margin: 3px;
  
    
}
</style>