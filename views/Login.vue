<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  computed: {
    isEmployee() {
      if (this.$store.state.user.authorities === undefined) {
        return false;
      }
      
      return this.$store.state.user.authorities.find( b => b.name == "ROLE_ADMIN") != null;
    },
  },
  methods: {
    login() {
      // console.log(this.user);
      authService
        .login(this.user)
        .then(response => {
          
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            
            if(this.isEmployee) {
              this.$router.push("/admin");
            } else {
              this.$store.commit("SET_USER_ON_ORDER");
              this.$router.push("/");
            }
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style>
h1{
  color:black;
}
#login {
  color:rgb(17, 17, 16);
  /* background-color: gray; */
 
  
  

}


label{
  color:black;
  font-size:2em;
  display: flex;
  justify-content:space-between;
  flex-wrap: nowrap;
  
 
  

}
</style>