<template>
    <div>
        <div class="title-button-container">
            <h3 class="title">Pizza Availability</h3>
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} Pizzas</button>
        </div>
        <div v-if="toggle">
        <div class="pizza-container" v-for="Pizza in specialtyPizzas" v-bind:key="Pizza.name">
            <div class="pizza-label">
            {{Pizza.name}}
            </div>
            <div class="button-container">
                <button v-on:click.prevent="toggleSpecialPizza(Pizza.available_item_id)">{{Pizza.is_available ? "Available" : "Not Available"}}</button>
                <button v-on:click.prevent="deleteSpecialPizza(Pizza.available_item_id)">Delete Pizza</button>
            </div>
        </div>
        </div>
    </div>

</template>
<script>

import PizzaService from '../services/PizzaService';
export default {
    data(){
        return {
            specialtyPizzas: "",
            toggle: false
        }
    },
    created() {
        PizzaService.getSpecialtyPizza().then(response => {
            this.specialtyPizzas = response.data;
        })
    },
    methods: {
        toggleSpecialPizza(id){
            PizzaService.toggleSpecialtyPizza(id).then(() =>{
                PizzaService.getSpecialtyPizza().then(response => {
            this.specialtyPizzas = response.data;
        });
            });
        },
        deleteSpecialPizza(id){
            PizzaService.deleteSpecialtyPizza(id).then(()=>{
                PizzaService.getSpecialtyPizza().then(response =>{
                    this.specialtyPizzas = response.data;
                })
            })
        }
    }
    
}
</script>
<style scoped>

button {
    border-radius: 5px;
}

.title-button-container {
    display: flex;
    align-content: center;
    align-items: center;
}

.title-button-container > button {
    height: auto;
    margin-left: 1.5vw;
}

.title {
    color: black;
    font-size: 3vw;
}

.pizza-container {
    display: flex;
    justify-content: space-between;
}
</style>