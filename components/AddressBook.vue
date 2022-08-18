<template>
    <div>
        <address-list v-for="address in addresses" v-bind:key="address.users_address_id" v-bind:address="address"/>
    </div>
</template>

<script>
import AddressList from '../components/AddressList.vue'
export default {
    components:{
        AddressList
    },
    props: {
        addresses: Array
    },
}
</script>