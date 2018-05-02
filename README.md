# CabService
Cab service implementation

## Cab Booking
### Cab controller
Customer will request for cab a location with a cab type. By default cab type is standard

### Cab service
Service will look in available cab repo for type of cab availability 

### Cab repo
Repo will return cab after comparing the distance of each cab available and assign it a booking id move it to booked cab map

## Cab Release
### Cab controller
On stop of journey cab will get released with user released location, booking id and cab type 

### Cab service
Cab service will find the distance between the start and end of journey and calculate the total fare

### Cab repo
Cab repo will move the release cab back to freeCab pool with customer home or destination address
