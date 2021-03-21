package sa.aqarz.Fragment.mapsHome;


import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.RegionModules;

public class MapsViewModel extends ViewModel {

    public static MutableLiveData<List<RegionModules>> mutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<List<CityLocation>> mutableLiveData_city = new MutableLiveData<>();


    MapsRepository mapsRepository;

//    public MainViewModel() {
//        videoRepository = new VideoRepository();
//    }

    public void getRegoins(Activity activity) {
        mapsRepository = new MapsRepository(activity);
        mapsRepository.getOpration();
    }

    public void getCity(Activity activity,String state_id) {
        mapsRepository = new MapsRepository(activity);
        mapsRepository.getCity( state_id);
    }
    public void getEstate_map(Activity activity,final String requestType, String url) {
        mapsRepository = new MapsRepository(activity);
        mapsRepository.getEstatMaps( requestType,url);
    }


    public LiveData<List<RegionModules>> getRegions_list() {
        return mutableLiveData;
    }

    public LiveData<List<CityLocation>> getCity_list() {
        return mutableLiveData_city;
    }


}
