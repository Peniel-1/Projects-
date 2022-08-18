<template>
    <div id="admin-view">
        <h1>Employee Menu</h1>
        <!-- show active orders -->
        <admin-active-orders/>
        <!-- add specialty pizza => NewPizzaForm.vue -->
        <admin-add-specialty/>
        <!-- toggle pizza availability -->
        <admin-toggle-pizza-availability/>
        <!-- toggle topping availability -->
        <admin-toggle-toppings/>
        <!-- show total order history -->
        <admin-order-history />
    </div>
</template>

<script>
import AdminActiveOrders from '../components/AdminActiveOrders.vue'
import AdminAddSpecialty from '../components/AdminAddSpecialty.vue'
import AdminOrderHistory from '../components/AdminOrderHistory.vue'
import AdminTogglePizzaAvailability from '../components/AdminTogglePizzaAvailability.vue'



import AdminToggleToppings from '../components/AdminToggleToppings.vue'
export default {
  components: { AdminActiveOrders, AdminAddSpecialty,AdminToggleToppings, AdminTogglePizzaAvailability, AdminOrderHistory },
    
}
</script>
<style scoped>
* {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
#admin-view {
    display: flex;
    flex-direction: column;
    align-items: center; 
}
h1 {
    font-size: 3.5vw;   
}
</style>