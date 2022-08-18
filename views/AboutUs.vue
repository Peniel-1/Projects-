<template>
  <body>
    <!-- <div class="header">
        <h1 class="title">About Us</h1>
        <about-us class="aboutus"></about-us>
    </div> -->

    <div class="hover">
      <div class="image-container">
        <img src="@/assets/home.jpg" alt="ab" class="homepicture3" />
        <!-- div.about-container -->
        <div class="hours-of-operation">
          <table>
            <thead>
              <tr>
                <th>Day</th>
                <th>Business Hours</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>MON - FRI</td>
                <td>11 AM - 1 AM</td>
              </tr>
              <tr>
                <td>SAT</td>
                <td>10 AM - 2 AM</td>
              </tr>
              <tr>
                <td>SUN</td>
                <td>CLOSED</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="about-us">
          <p class="about-us-text">
            We welcome you to Pizza D'azzurro. Come and experience Italian
            cuisine the way it was meant to be enjoyed. Our quick elevated
            atmosphere paired with our love of authentic Italian food makes us a
            favorite in our communities for quick lunches and dinners as well as
            larger gatherings for special occasions. We are passionate about
            providing the highest food standards and over the last few years we
            have been successful in developing a culinary style of our own that
            has led to the success of Pizza D'azzurro. Within a short period
            from inception, Pizza D'azzurro, turned into a favorite restaurant
            in the neighborhood that was frequented by friends and families to
            enjoy moments of dining nostalgia.
          </p>
        </div>
      </div>
    </div>
  </body>
</template>


<script>
// import AboutUs from "@/components/AboutUs";

export default {
  // components: {
  //     AboutUs
  // }
};
</script>

<style>
.title{
    color:gray;
    font-family:  italic 1.2em "Fira Sans", serif;
    
}

.aboutus {
  /* background:rgb(133, 130, 130); */
  color: white;

  font-size: 30px;
  font-family: cursive;
}
.about-footer {
  text-shadow: -2px -2px -2px blue;
  position: relative;
  transform: translate(-50%, -50%);
  margin-top: 100px;
}
.about-us {
  text-shadow: -1px 2px 2px #a71e5e;
  font-variant: small-caps;
  color: azure;
  font-family: allan;
  /* font-size: 2em; */
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.about-us-text {
  font-size:1.9vw;
}
.hover {
  position: relative;
  text-align: center;
  color: rgb(0, 0, 0);
  background-color: black;
}
.homepicture3 {
  object-fit: cover;
  border-radius: 25px;
  width: 90%;
  background-color: black;
  margin: 5vw;
  opacity: 0.4;
  min-height: 100vh;

}

th,
td {
  padding: 20px;
}
.image-container {
  display: flex;
  position: relative;
}

.hours-of-operation {
  position: absolute;
  bottom: 10%;
  left: 10%;
  color: azure;
  /* font-size: 2em; */
  font-size: 2vw;
  padding: 0 20%;
  
}
@media only screen and(max-width: 1028 px) {
.hours-of-operation {
  position: absolute;
  bottom: 10%;
  /* left: 15%; */
  color: azure;
  /* font-size:0.5em; */
  font-size: 2vw;
  padding: 0 20%;
}
  .about-us {
  text-shadow: -1px 2px 2px #a71e5e;
  font-variant: small-caps;
  color: azure;
  font-family: allan;
  font-size: 2em;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);

  }
  


  }

</style>