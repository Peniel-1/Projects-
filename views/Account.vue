<template>
    <div>
        <h2 class="title">Your Profile</h2>
        <!-- show user orders -->
        <user-order-history/>
        <!-- show/edit saved addresses -->
        <user-saved-address/>
        <!-- show/edit saved payment options -->
        <user-saved-card/>
        
    </div>
</template>

<script>


import UserOrderHistory from '../components/UserOrderHistory.vue'
import UserSavedAddress from '../components/UserSavedAddress.vue'
import UserSavedCard from '../components/UserSavedCard.vue'

export default {
    components: {
        UserOrderHistory, 
        UserSavedAddress, 
        UserSavedCard,
    },
}
</script>

<style scoped>
.title {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>