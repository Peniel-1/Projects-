<template>
    <div>
        <h4>Saved Payment Options</h4>
        <p>Payment Name: {{payment.payment_name}}</p>
        <p>Expiration Date: {{payment.expiration_date}}</p>
        <button v-on:click.prevent="deletePayment()">Delete Payment Option</button>
    </div>
</template>

<script>
import AuthService from '../services/AuthService'
export default {
    props: {
        payment: Object
    },
    methods:{
        deletePayment(){
            AuthService.deletePayment(this.payment.users_payment_id);
        }
    }
    
}
</script>