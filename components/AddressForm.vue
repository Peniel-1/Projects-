<template>
    <div>
        <h4>Address Form</h4>
        <!-- <p>This will be passed to API: {{newAddress}}</p> -->
        <form class="address-container">
            <label for="addressName">Address Nickname: </label>
            <input id="addressName" type="text" v-model="newAddress.address_name" />
            <label for="addressLineOne">Address Line 1: </label>
            <input id="addressLineOne" type="text" v-model="newAddress.address_line_one" />
            <label for="addressLineTwo">Address Line 2: </label>
            <input id="addressLineTwo" type="text" v-model="newAddress.address_line_two" />
            <label for="city">City: </label>
            <input id="city" type="text" v-model="newAddress.city" />
            <label for="zip">Zip Code: </label>
            <input id="zip" type="text" v-model="newAddress.zip" placeholder="e.g. 12345" />
            <button v-on:click.prevent="saveAddress()">Save</button>
        </form>
    </div>
</template>

<script>
import AuthService from '../services/AuthService.js';

export default {
    name: "new-address-form",
    data() {
        return {
            newAddress: {
                user_id: this.$store.state.user.id,
                address_name: '',
                address_line_one: '',
                address_line_two: '',
                city: '',
                zip: ''
            }
        }
    },
    methods: {
        saveAddress() {
            AuthService.saveAddress(this.newAddress).then( response => {
                console.log(response.data);
                this.resetAddress();
                this.$emit('reset-address');
            });
        },
        resetAddress() {
            this.newAddress = {
                user_id: this.$store.state.user.id,
                address_name: '',
                address_line_one: '',
                address_line_two: '',
                city: '',
                zip: ''
            };
        }
    }
}
</script>

<style>
.address-container {
    padding: 1px;
}
</style>