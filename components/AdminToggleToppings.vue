<template>
    <div class="change-options-container">
        <div class="title-button-container">
            <h3 class="title">Edit Pizza Options:</h3>
            
        <div class="title-button-container">
            <!-- <h3 class="title">Change {{title}}</h3> -->
            <button @click="toggle = !toggle">{{ toggle ? "Hide" : "Show"}} Options</button>
        </div>
        </div>
        <div class="topping-list-container" v-if="toggle">
            <div class="select-container">
            <span style="font-size: 2vw">Change</span>
            <select class="select-form" v-model="optionChoice" @change="selectChange()">
                <option value="Toppings">Toppings</option>
                <option value="Crust">Crust</option>
                <option value="Sauce">Sauce</option>
            </select>
            </div>
            <admin-change-toppings @refresh="selectChange" v-bind:title="optionChoice" v-bind:options="optionArray" />
        </div>
    </div>
</template>
<script>
import AdminChangeToppings from './AdminChangeToppings.vue';
import PizzaService from '../services/PizzaService.js';

export default {
  components: { AdminChangeToppings },
    name: "change-options",
    data() {
        return {
            optionChoice: "Toppings",
            optionArray: [],
            toggle: false
        }
    },
    methods: {
        selectChange() {

            if(this.optionChoice=="Toppings"){
                PizzaService.getToppingsDetail().then( response => {
                    const tempArray = [];
                    response.data.forEach(item => {
                        tempArray.push({
                            id: item.topping_id,
                            name: item.name,
                            isAvailable: item.topping_is_available,
                            isPremium: item.topping_is_premium,
                            price: item.topping_premium_price
                        });
                    });
                    this.optionArray = tempArray;
                });
            } else if(this.optionChoice == "Crust") {
                PizzaService.getOptions(1,2).then( response => {
                    const tempArray = [];
                    response.data.forEach(item => {
                        tempArray.push({
                            id: item.itemOptionId,
                            name: item.option,
                            isAvailable: item.available,
                            isPremium: item.premium,
                            price: item.price
                        });
                    });
                    this.optionArray = tempArray;
                });
            } else if(this.optionChoice == "Sauce") {
                PizzaService.getOptions(1,3).then( response => {
                    const tempArray = [];
                    response.data.forEach(item => {
                        tempArray.push({
                            id: item.itemOptionId,
                            name: item.option,
                            isAvailable: item.available,
                            isPremium: item.premium,
                            price: item.price
                        });
                    });
                    this.optionArray = tempArray;
                });
            }
        }
    },
    created() {
        this.selectChange();
    }    
}
</script>
<style scoped>

button {
    border-radius: 5px;
}

.title-button-container {
    display: flex;
    justify-items: flex-start;
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

.select-container {
    margin-bottom: 2vw;
}

.select-form {
    font-size: 1.5vw;
    margin-left: 1vw;
    margin-top: 0.2vw;
}


</style>