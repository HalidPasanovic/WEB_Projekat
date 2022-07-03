Vue.component("viewFacility", {
	data: function () {
		return {
			id: -1,
			user: null,
			facility: null,
			comments: null,
			training: null,
			canComment: false
		}
	},
	template: ` 
<div style="width: 100%;">
	<div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
		<div style="width: 500px">
			<img src="pictures/ProfilePlaceholderSuit.svg" alt="" width="100" height="100"
				class="rounded-circle me-2" style="margin-bottom: 25px; margin-left: 38%;">
			<div>
				<label for="Name" class="form-label">Name</label>
				<input type="text" class="form-control" id="Name" placeholder="" :value="facility?.name" disabled>
			</div>
			<div>
				<label for="Type" class="form-label">Type</label>
				<input type="text" class="form-control" id="Type" :value="facility?.type?.name" placeholder="" disabled>
			</div>
			<div class="form-check" style="margin-top: 25px; margin-bottom: 25px;">
				<input type="checkbox" class="form-check-input" id="same-address" :value="facility?.status" disabled>
				<label class="form-check-label" for="same-address">Renovating currently</label>
			</div>
			<div v-if="checkIfRatingExists(facility?.id)">
				<label for="Type" class="form-label">Rating</label>
				<input type="number" class="form-control" id="Type" placeholder="" :value="getRating(facility?.id)" disabled>
			</div>
		</div>
		<div style="margin-left: 50px;">
			<div id="map" style="width: 600px; height: 410px;"></div>
			<div id="popup" class="ol-popup">
				<a href="#" id="popup-closer" class="ol-popup-closer"></a>
				<div id="popup-content"></div>
			</div>
		</div>
	</div>
	<div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
		<div v-if="comments?.length">
			<div style="display: flex; justify-content: center; margin-top: 40px;">
				<h3>Comments</h3>
			</div>
			<table class="table table-striped table-hover table-dark">
				<thead>
					<tr>
						<th scope="col">Customer</th>
						<th scope="col">Comment</th>
						<th scope="col">Rating</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(f, index) in comments">
						<td>{{f?.customer?.name}}</td>
						<td>{{f?.comment}}</td>
						<td>{{f?.rating}}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div v-if="training?.length" style="margin-left: 50px;">
			<div style="display: flex; justify-content: center; margin-top: 40px;">
				<h3>Trainings</h3>
			</div>
			<table class="table table-striped table-hover table-dark">
				<thead>
					<tr>
						<th scope="col">Trainer</th>
						<th scope="col">Description</th>
						<th scope="col">Name of trainer</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(f, index) in training">
						<td><img :src="'pictures/' + f?.trainer?.pictureLocation" alt="" width="40" height="40"
						class="rounded-circle me-2" style="margin-bottom: 25px;"></td>
						<td>{{f?.description}}</td>
						<td>{{f?.trainer?.name}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
		<div>
			<button class="w-100 btn btn-lg btn-dark" style="margin-top: 50px;">Visit facility</button>
		</div>
		<div v-if="canComment">
			<button class="w-100 btn btn-lg btn-dark" style="margin-top: 50px; margin-left: 25px;" v-on:click = "CreateComment">Leave a comment</button>
		</div>
	</div>
</div>		  
`
	,
	methods: {
		editProduct: function () {
			event.preventDefault();
			if (this.id != -1) {
				axios.put('rest/products/' + this.product.id, this.product).
					then(response => (router.push(`/`)));
			}
			else {
				axios.post('rest/products', this.product).
					then(response => (router.push(`/`)));
			}
		},

		isEmpty : function(obj) {
			var result = obj && Object.keys(obj).length === 0 && Object.getPrototypeOf(obj) === Object.prototype
			if(result === false){
				return true;
			}
			return result
		},

		getRating: function(id) {
			var rating = 0;
			axios
				.get('rest/comment/rating/' + id)
				.then(response => (
					rating = response.data))
			return rating
		},

		checkIfRatingExists: function(id){
			var rating = this.getRating(id)
			if(rating === 0){
				return false
			}
			return true;
		},

		CreateComment: function(){
			router.push(`/`)
		},

		CheckIfCanLeaveComment : function() {
			return true;
		}
	},
	mounted() {
		this.id = this.$route.params.id;
		if (this.id != -1) {
			axios
				.get('rest/facility/' + this.id)
				.then(response => (
					this.facility = response.data,
					instantiateMap(response.data)))
			axios
				.get('rest/facility/comments/' + this.id)
				.then(response => (
					this.comments = response.data))
			axios
				.get('rest/facility/trainings/' + this.id)
				.then(response => (
					this.training = response.data))
			axios
          		.get('rest/login/loginstat')
          		.then(response => 
				{
					user = response.data;
					axios
						.post('rest/comment/canComment/'+ this.id, user)
						.then(response => 
						{
							this.canComment = response.data;
						})
				})
		}
	}
});

function instantiateMap(data) {
	var attribution = new ol.control.Attribution({
		collapsible: false
	});

	var position = [data?.location?.longitude, data?.location?.latitude]
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

	closer.onclick = function () {
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
