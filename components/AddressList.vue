<template>
    <div>
        <h4>Address</h4>
        <p>Address Name: {{address.address_name}}</p>
        <p>Address: {{address.address_line_one}}</p>
        <p>Address: {{address.address_line_two}}</p>
        <p>City: {{address.city}}</p>
        <p>Zip: {{address.zip}}</p>
        <button v-on:click.prevent="deleteAddress()">Delete Address</button>
    </div>
</template>

<script>
import AuthService from '../services/AuthService'
export default {
    name: "address-list",
    props: {
        address: Object
    },
    methods: {
        deleteAddress() {
            AuthService.deleteAddress(this.address.users_address_id);
        }
    }


}
</script>