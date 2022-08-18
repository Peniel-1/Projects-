<template>
    <div>
        <div class="title-button-container">
            <h3 class="title">Order History</h3>
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} History</button>
        </div>
        <div class="order-details-container" v-if="toggle">
        <order-details v-for="order in orderHistory" v-bind:key="order.order_id" v-bind:order="order" />
        </div>
    </div>
</template>

<script>
import OrderService from '../services/OrderService.js';
import OrderDetails from './OrderDetails.vue';

export default {
    name: "admin-order-history",
    components: {
        OrderDetails,
        
    },
    data() {
        return {
            orderHistory: [],
            toggle: false
        }
    },
    methods: {
        getAllOrders() {
            OrderService.getAll().then ( response => {
                this.orderHistory = response.data;
            });
        }
    },
    created() {
        this.getAllOrders();
    },
    updated() {
        // this.getAllOrders();
    }
}
</script>

<style scoped>

button {
    border-radius: 5px;
}

.title-button-container {
    display: flex;
    justify-content: center;
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

.order-details-container {
    margin: 0 2vw;
    display: flex;
    flex-wrap: wrap;
}
</style>