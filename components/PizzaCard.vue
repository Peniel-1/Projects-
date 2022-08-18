<template>
    <div id="PizzaCard">
       <h3 class="name">
           <router-link class="link" v-bind:to="{ name: 'menu-details', params: { itemName: pizza.name } }">{{pizza.name}}</router-link>
        </h3>
        <p>{{pizza.description}}</p>
        <p>${{pizza.price}}</p>
    </div>  
</template>

<script>
export default {
    name: "pizza-card",
    data() {
        return {
            description: "its pizza"
        };
    },
    props: {
        pizza: Object
    },
    methods: {}
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Rubik+Distressed&display=swap');
div {
    
    border-radius: 5px;
    margin: 3px;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    align-items: center;
    
    
}
.link {
    color: goldenrod;
    text-shadow: 2px 2px black;
    font-size: larger;
    display: flex;
    justify-content: center;
  
}
#PizzaCard {
    
  font-family: 'Rubik Distressed', cursive;
  color: white;
  text-shadow: 2px 2px black;
  font-size: 2em;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items:center;
}



 

</style>
    