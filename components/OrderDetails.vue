<template>
    <div class="order-history-container">
        <div class="basic-order-info">
            <p class="blurb">Order #: {{order.order_id}}</p>
            <p class="blurb">Ordered by: {{order.order_user_name}}</p>
        </div>
        <div class="more-order-info">
            <p class="blurb">Pick-up or Delivery: {{order.order_type_name}}</p>
            <p class="blurb">Order Status: {{order.order_status_name}}</p>
        </div>
        <div class="order-details">
            <p class="blurb">Total: ${{order.total}}</p>
            <p class="blurb">Payment Type: {{order.payment_type}}</p>
            <p class="blurb">Number of items: {{order.items.length}}</p>
        </div>
        <div class="item-details">
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} Items</button>
            <div class="detail-wrapper" v-if="toggle">
                <purchased-item-details v-for="item in order.items" v-bind:key="item.purchased_item_id" v-bind:item="item" />
            </div>
        </div>
    </div>
</template>

<script>
import PurchasedItemDetails from './PurchasedItemDetails.vue';

export default {
    name: "order-details",
    data() {
        return {
            toggle: false
        }
    },
    components: {
        PurchasedItemDetails
    },
    props: {
        order: Object
    }
}
</script>

<style scoped>
* {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

button {
    border-radius: 5px;
}

.order-history-container {
    display: flex;
    flex-direction: column;
    border: 1px darkred solid;
    border-radius: 12px;
    margin: 2vh 2vw;
    color: lightgray;
}
.basic-order-info {
    display: flex;
    justify-content: space-between;
}
.more-order-info {
    display: flex;
    justify-content: space-between;
}
.order-details {
    display: flex;
    justify-content: space-between;
}
.blurb {
    font-size: 1.5vw;
    padding: 0 0.5vw;
    text-decoration: underline;
}

</style>