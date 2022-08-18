<template>
    <div>
        <h4>Add Credit Card</h4>
        <!-- <p>This will be passed to API: {{newPayment}}</p> -->
        <form>
            <label for="paymentName">Card Nickname: </label>
            <input id="paymentName" type="text" v-model="newPayment.payment_name" />
            <label for="paymentCcn">Card Number: </label>
            <input id="paymentCcn" type="text" v-model="newPayment.ccn" placeholder="1234567890123456" />
            <label for="expirationDate">Expiration Date: </label>
            <input id="expirationDate" type="text" v-model="newPayment.expiration_date" placeholder="mm/yy" />
            <button v-on:click.prevent="savePayment()">Save</button>
        </form>
    </div>
</template>

<script>
import AuthService from '../services/AuthService.js';

export default {
    name: "new-payment-form",
    data() {
        return {
            newPayment: {
                user_id: this.$store.state.user.id,
                payment_name: '',
                ccn: '',
                expiration_date: ''
            }
        }
    },
    methods: {
        savePayment() {
            AuthService.savePayment(this.newPayment).then( response => {
                console.log(response.data);
                this.resetPayment();
                this.$emit('reset-card');
            });
        },
        resetPayment() {
            this.newPayment = {
                user_id: this.$store.state.user.id,
                payment_name: '',
                ccn: '',
                expiration_date: ''
            };
        }
    }
}
</script>

<style scoped>

</style>