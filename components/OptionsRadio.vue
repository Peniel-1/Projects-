<template>
    <div>
        {{options}}
    </div>
</template>

<script>
export default {

    name: "options-radio",
    props: {
        name: String,
        options: Array  // options object needs name, option: {[name, index]}
    },
}
</script>

<style scoped>

</style>