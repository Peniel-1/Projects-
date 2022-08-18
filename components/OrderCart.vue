<template>
    <div>
        <p id="cart" v-on:click="goToCheckout">Cart: {{getCartMessage()}}</p>
        
        
        <img v-on:click="goToCheckout" src="@/assets/cart.png" alt="dinner" class="homepicture7">
  
      
         <!-- <img src="@/assets/image.jpg" alt="dinner" class="homepicture1"> -->
    </div>
</template>

<script>
export default {
    name: "cart",
    methods: {
        goToCheckout() {
            this.$router.push('/checkout');
        },
        getCartMessage() {
            if(this.$store.state.order.items.length > 0) {
                return "$" + this.$store.state.order.total + " (" + this.$store.state.order.items.length + ")";
            } else {
                return "Empty";
            }
        },
        removeItem(){}
    }
}
</script>

<style scoped>
#cart {
    display: stretch;
    max-width: 125px;
    color: rgb(255, 253, 253);
    /* border: 2px red solid; */
    /* background-color: rgb(233, 34, 34); */
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

}

</style>