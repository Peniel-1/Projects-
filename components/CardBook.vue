<template>
    <div>
        <saved-card v-for="payment in payments" v-bind:key="payment.users_payment_id" v-bind:payment="payment"/>
    </div>
</template>

<script>
import SavedCard from '../components/SavedCard.vue'
export default {
    components:{
        SavedCard
    },
    props: {
        payments: Array
    }
}
</script>