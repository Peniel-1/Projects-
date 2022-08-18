<template>
    <div>
        <h2>Your Addresses</h2>
        {{toggle}}
        <address-book v-bind:addresses="listOfAddresses"/>
        <button @click="toggle = !toggle">Add New Address</button>
        <address-form v-if="toggle"/>
    </div>
</template>

<script>
import AuthService from '../services/AuthService'
import AddressBook from '../components/AddressBook.vue'
import AddressForm from '../components/AddressForm.vue'
export default {
    name: "user-saved-address",
    components: {
        AddressBook,
        AddressForm
    },
    data(){
        return{
            listOfAddresses: [],
            
        }
    },
    created(){
        AuthService.getAddressBook(this.$store.state.user.id).then(b =>{
            this.listOfAddresses = b.data
        });
        
    },
    updated(){
        AuthService.getAddressBook(this.$store.state.user.id).then(b =>{
            this.listOfAddresses = b.data
            });

        
    },
    

}
</script>

<style scoped>
h2 {
    color: rgb(14, 14, 14);
    font-size: 2.5em;
}
</style>