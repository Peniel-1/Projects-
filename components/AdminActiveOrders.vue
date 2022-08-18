<template>
    <div>
        <div class="title-button-container">
            <h2 class="title">Active Orders</h2>
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} Active Orders</button>
        </div>
        <div v-if="toggle">
            <order v-for="order in pendingOrders" v-bind:key="order.order_id" v-bind:order="order"/>
        </div>
    </div>
</template>
<script>
import OrderService from '../services/OrderService'
import Order from './Order.vue';
export default {
  components: { Order },
    data() {
        return{
            pendingOrders: [],
            toggle: false
        }
    },
    created(){
        OrderService.getActiveOrders().then(n => {this.pendingOrders=n.data});
    },
     updated(){
        OrderService.getActiveOrders().then(n => {this.pendingOrders=n.data});
    },
    methods: {
        
       
    }
    
}
</script>
<style scoped>

button {
    border-radius: 5px;
}

.title-button-container {
    display: flex;
    align-content: center;
    align-items: center;
}

.title-button-container > button {
    height: auto;
    margin-left: 1.5vw;
}

.title {
    color: black;
    font-size: 3vw;
}
</style>