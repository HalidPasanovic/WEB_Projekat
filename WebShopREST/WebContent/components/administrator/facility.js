Vue.component("facillity", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null,
	      pomocna: null,
	      trainings: null,
	      comments : null,
	      temp : null	
	    }
	},
	    template: `
	    <main class="d-flex flex-nowrap" style="overflow-y: auto;">
	    <div style="width: 70%; margin: auto;">
	    <div style="display: flex; justify-content: center;">
                    <h3>Facility Details</h3><br>
        </div>
        <table v-if="facilities" class="table table-striped table-hover table-dark">
	    <tbody>
	    <tr>
	    <td>Name:<td>
	    <td>{{facilities.name}}</td>
	    </tr>
	    <tr>
	    <td>Type:<td>
	    <td>{{facilities.type.name}}</td>
	    </tr>
	    <tr>
	    <td>Status:<td>
	    <td>{{facilities.status}}</td>
	    </tr>
	    <tr>
	    <td>Work Range:<td>
	    <td>{{facilities.workRange}}</td>
	    </tr>
	    <tr>
	    <td>Address:<td>
	    <td>{{facilities.location.adress.street + " " + facilities.location.adress.number + " " + facilities.location.adress.place}}</td>
	    </tr>
	    <tr>
	    <td>Average Grade:<td>
	    <td>0.0</td>
	    </tr>
	    <tr>
	    <td>Logo:<td>
	    <td><img v-bind:src="this.temp" alt="Logo of the Sport Facillity" width="50" height="50"></td>
	    </tr>
	    </tbody>
	    </table>
	    <div style="margin-left: 18%;">
			<div id="map" style="width: 600px; height: 410px;"></div>
			<div id="popup" class="ol-popup">
				<a href="#" id="popup-closer" class="ol-popup-closer"></a>
				<div id="popup-content"></div>
			</div>
		</div>
        <br><h5>Trainings in this facility:</h5>
                    <table class="table table-striped table-hover table-dark">
                    		<thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Type Name</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Description</th>
                                <th scope="col">Trainer</th>
                            </tr>
                            </thead>
                            <tbody>
                        <tr v-for="(t, index) in trainings">
						<td>{{t.name}}</td>
						<td>{{t.type.name}}</td>
						<td>{{t.duration}}</td>
						<td>{{t.description}}</td>
						<td>{{t.trainer.name}}</td>
						</tr>
						</tbody>
                    </table>
        <br><h5>Comments for this facility:</h5>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Customer name</th>
	    <th>Content</th>
	    <th>Rating</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(c, index) in comments">
					<td>{{c.customer.name}}</td>
					<td>{{c.content}}</td>
					<td>{{c.rating}}</td>
		</tr>
		</tbody>
		</table>
	    </div>
	    </main>
    	`,
    mounted () {
		this.pomocna = this.$route.params.id;
		axios
			.get('rest/facility/' + this.pomocna)
				.then(response => {this.facilities = response.data; instancem(response.data); this.temp = "pictures/" + this.facilities.logoLocation;})
				.catch((e) => { alert("Exception")})
				
		axios
			.get('rest/facility/trainings/' + this.pomocna)
				.then(response => (this.trainings = response.data))
				.catch((e) => { alert("Exception")})
		
		axios
			.get('rest/facility/comments/' + this.pomocna)
				.then(response => (this.comments = response.data))
				.catch((e) => { alert("Exception")})
		
    },
    methods: {
	},
    computed: {
    resultQuery(){
      if(this.searchQuery){
      return this.facilities.filter((item)=>{
        return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
        	|| item.type.name.toLowerCase().includes(v) 
        	|| item.location.adress.street.toLowerCase().includes(v) 
        	|| item.location.adress.number.toString().toLowerCase().includes(v)
        	|| item.location.adress.place.toLowerCase().includes(v))
      })
      }else{
        return this.facilities;
      }
    }
  }
});

function instancem(data) {
	var attribution = new ol.control.Attribution({
		collapsible: false
	});

	var position = [data.location.longitude, data.location.latitude]
	var location = data?.location?.adress?.street + " " + data?.location?.adress?.number + " " + data?.location?.adress?.place

	var map = new ol.Map({
		controls: ol.control.defaults({ attribution: false }).extend([attribution]),
		layers: [
			new ol.layer.Tile({
				source: new ol.source.OSM()
			})
		],
		target: 'map',
		view: new ol.View({
			center: ol.proj.fromLonLat(position),
			maxZoom: 18,
			zoom: 9
		})
	});

	var layer = new ol.layer.Vector({
		source: new ol.source.Vector({
			features: [
				new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.fromLonLat(position))
				})
			]
		})
	});

	map.addLayer(layer);

	var container = document.getElementById('popup');
	var content = document.getElementById('popup-content');
	var closer = document.getElementById('popup-closer');

	var overlay = new ol.Overlay({
		element: container,
		autoPan: true,
		autoPanAnimation: {
			duration: 250
		}
	});
	map.addOverlay(overlay);

	closer.onclick = function f() {
		overlay.setPosition(undefined);
		closer.blur();
		return false;
	};

	map.on('singleclick', function (event) {
		if (map.hasFeatureAtPixel(event.pixel) === true) {
			var coordinate = event.coordinate;

			content.innerHTML = '<b>' + location + '</b>';
			overlay.setPosition(coordinate);
		} else {
			overlay.setPosition(undefined);
			closer.blur();
		}
	});

	content.innerHTML = '<b>' + location + '</b>';
	overlay.setPosition(ol.proj.fromLonLat(position));
}