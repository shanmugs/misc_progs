import 'package:flutter/foundation.dart';
import 'package:nearest_hospitals/constants/error_messages.dart';
import 'package:nearest_hospitals/customError/fault.dart';
import 'package:nearest_hospitals/services/places_service.dart';

import 'package:nearest_hospitals/models/venue.dart';

class PlacesNotifier extends ChangeNotifier {
  static String _clientId = '';

  static String _clientSecret = '';
  static String _apiVersion = '20190425';
  static String _radius = '4000';

  var _placeService = PlacesService();

  Fault _placesNotifierFault;

  Venue _venue;

  bool _isLoadingVenues = true;

  setFault(Fault fault) => _placesNotifierFault = fault;

  Fault get getFault => _placesNotifierFault;

  setVenue(Venue venue) => _venue = venue;

  Venue get getVenue => _venue;

  setIsLoadingVenues(bool isLoading) {
    _isLoadingVenues = isLoading;
    notifyListeners();
  }

  bool get isloadingVenues => _isLoadingVenues;

  getPlaces(String latitude, String longitude) async {
    setIsLoadingVenues(true);
    try {
      Venue venue = await _placeService.getNearestPlaces(
          _clientId, _clientSecret, _apiVersion, latitude, longitude, _radius);
      setVenue(venue);
    } on Fault catch (e) {
      setFault(e);
    } catch (e) {
      setFault(Fault(message: UNKNOWN_ERROR));
      print("Erorr occurred: ${e.toString()}");
    }
    setIsLoadingVenues(false);
  }
}
