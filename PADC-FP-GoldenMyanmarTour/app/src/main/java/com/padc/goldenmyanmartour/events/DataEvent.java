package com.padc.goldenmyanmartour.events;

import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/24/2016.
 */
public class DataEvent {

    public static class SuccessEvent {
        //        private DestinationVO destinationVO;
        private ArrayList<DestinationVO> mDestinationList;

//        public SuccessEvent(DestinationVO destinationVO) {
//            this.destinationVO = destinationVO;
//        }

        public SuccessEvent(ArrayList<DestinationVO> destinationVOArrayList) {
            this.mDestinationList = destinationVOArrayList;
        }

//        public DestinationVO getDestinationVO() {
//            return destinationVO;
//        }

        public ArrayList<DestinationVO> getmDestinationList() {
            return mDestinationList;
        }
    }

    public static class FailedEvent {
        private String message;

        public FailedEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class DestinationDataLoaded {
        private String message;
        private List<DestinationVO> destinationVOList;

        public DestinationDataLoaded(String message, List<DestinationVO> destinationVOList) {
            this.message = message;
            this.destinationVOList = destinationVOList;
        }

        public String getMessage() {
            return message;
        }

        public List<DestinationVO> getDestinationVOList() {
            return destinationVOList;
        }
    }

    public static class PackageDataLoaded {
        private String message;
        private List<PackageVO> packageVOList;

        public PackageDataLoaded(String message, List<PackageVO> packageVOList) {
            this.message = message;
            this.packageVOList = packageVOList;
        }

        public String getMessage() {
            return message;
        }

        public List<PackageVO> getPackageVOList() {
            return packageVOList;
        }
    }

}
