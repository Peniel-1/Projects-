<template id="map-template">
  <div>
    <!-- <div>
      <h2>Search and add a pin</h2>
      <GmapAutocomplete
        @place_changed='setPlace'
      />
      <button
        @click='addMarker'
      >
        Add
      </button>
    </div> -->
    <br>
    <div id="map-container">
      <h2>Find Us</h2>
      <h3>3101 S. Michigan St. Chicago, IL 60616</h3>
      <GmapMap
        :center='center'
        :zoom='14'
        style=''
      >
        <GmapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
          @click="center=m.position"
        />
      </GmapMap>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GoogleMap',
  data() {
    return {
      center: {lat: 41.8382523, lng: -87.6255779},
      markers: [{
        position: {
          lat: 41.8382523,
          lng: -87.6255779,
          label: "Pizza D'azzuro"
        }
      }],
      places: []
    }
  },
  methods: {
    handleDrag () {
      let center = {
        lat: this.map.getCenter().lat(),
        lng: this.map.getCenter().lng(),
      };
      let zoom = this.map.getzoom;
      localStorage.center = JSON.stringify(center);
      localStorage.zoom = zoom;
    },
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
  },
  computed: {
    mapCoordinates() {
      if (!this.map) {
        return {
          lat: 0,
          ing: 0,
        };
      }

      return {
        lat: this.map.getCenter().lat().toFixed(4),
        lng: this.map.getCenter().lng().toFixed(4),
      };
    },
  },
  // mounted() {
  //   this.geolocate();
  // },
};
</script>

<style>
h2{
  color: rgb(24, 23, 23);
  text-align: center;
}
#map-container {
  display: flex;
  position: relative;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  text-align: center;
 }
.vue-map-container {
  display: flex;
  justify-content: center;
  height: 400px;
  

}
.vue-map{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}
/*.vue-map {
  display: flex;
  width: 80%;
  align-content: space-around;
  justify-content: center;
  position: relative;
}
.gm-style {
  display: flex;
  position: relative;
  width: 50%;
  align-content: center;
  justify-content: center;
}
#map-template {
  background-color: grey;
}*/
</style>