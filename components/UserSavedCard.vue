<template>
    <div>
        <h2>Your Payment Options</h2>
        <card-book v-bind:payments="listOfPayments"/>
        <button @click="toggle = !toggle">Add New Payment</button>
       <credit-card-form v-if="toggle"/>
    </div>
</template>

<script>
import CreditCardForm from '../components/CreditCardForm.vue'
import CardBook from '../components/CardBook.vue'
import AuthService from '../services/AuthService'
export default {
    name: "user-saved-card",
    components:{
        CardBook,
        CreditCardForm
    },
    data(){
        return {
            listOfPayments:[],
            spanVisible: false
        }
    },
    created(){
        AuthService.getPaymentOptions(this.$store.state.user.id).then(b => {
            this.listOfPayments = b.data
        });
    },
    updated(){
        AuthService.getPaymentOptions(this.$store.state.user.id).then(b => {
            this.listOfPayments = b.data
        });
    }
  
}
</script>

<style scoped>
h2 {
    color: black;
    font-size: 2.5em;
}
</style>







