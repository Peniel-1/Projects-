<template>
    <div class="add-specialty-container">
        <div class="title-button-container">
            <h2 class="title">Add Specialty Pizza</h2>
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} Form</button>
        </div>      
        <form class="specialty-form" v-if="toggle" v-on:submit="addSpecialtyPizza">
            <div class="input-container">
                <label for="name">Name:</label>
                <input type="text" name="name" v-model="newSpecialtyPizza.name">
            </div>
            <div class="input-container">
                <label for="description">Description:</label><br>
                <input type="text" name="description" v-model="newSpecialtyPizza.description">
            </div>
            <div class="input-container">
                <label for="price">Price:</label><br>
                <input type="number" min="0.01" step="0.01" name="price" v-model="newSpecialtyPizza.price"><br>
            </div>
            <span class="topping-title">Toppings:</span>
            <div class="topping-container">
            <div class="topping-label-container" v-for="option in toppingOptions" v-bind:key="option.name">
                <input :id="option.name" type="checkbox" v-bind:value="option.index" v-model="newSpecialtyPizza.toppings"  />
                <label :for="option.name">{{option.name}}</label>
            </div>
            </div>
            <input class="submit" type="submit" value="Submit">
        </form>
    </div>
</template>
<script>
import PizzaService from '../services/PizzaService';
export default {
    data(){
        return{
            newSpecialtyPizza: {
                item_type_id: 1,
                name:"",
                description: "",
                price: 0,
                is_available: true,
                is_specialty: true,
                toppings: []
            },
            toppingOptions: [],
            toggle: false,
        }
    },
    created() {

        PizzaService.getByoToppings().then( response => {
            const optionArray = [];
            this.toppingOptions = optionArray;

            for (let i = 0; i < response.data.length; i++) {
                
                const element = {
                    name: response.data[i].name,
                    index: response.data[i].id,
                    isPremium: response.data[i].isPremium,
                    price: response.data[i].topping_premium_price,
                    isAvailable: true
                    };
                optionArray.push(element);
            }
        });
    },
    methods: {
        addSpecialtyPizza(){
            PizzaService.addSpecialtyPizza(this.newSpecialtyPizza).then(response =>{
                if(response.status === 201) {this.newSpecialtyPizza =  {
                name:"",
                description: "",
                price: 0,
                toppings: [],
                is_available: true,
                is_specialty: true
            }
                }
            })
        }
    }
}
</script>
<style scoped>
* {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

button {
    border-radius: 5px;
}

.add-specialty-container {
    max-width: 40%;
    height: auto;

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

.specialty-form {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.input-container, .topping-container {
    display: flex;
    flex-wrap: wrap;
    height: auto;
    align-items: center;
    justify-content: flex-start;
    min-width: 7vw;
    padding-left: 2.5vw;
}

.input-container {
    flex-direction: row;
}
.input-container > input
.input-container > label, .topping-title {
    font-size: 1.8vw;
    min-width: 6vw;
    padding-left: 2.5vw;
}
.topping-label-container {
    display: flex;
    min-width: 8vw;
}
.topping-container label {
    display: flex;
    align-items: center;
    font-size: 1.5vw;
    height: auto;
}
.topping-container input {
    height: auto;
}
.topping-container {
    display: flex;
    flex-direction: row;
    max-width: 20vw;
}
.submit {
    display: flex;
    align-items:center;
    justify-content: space-around;
    max-width: 5vw;
}

</style>