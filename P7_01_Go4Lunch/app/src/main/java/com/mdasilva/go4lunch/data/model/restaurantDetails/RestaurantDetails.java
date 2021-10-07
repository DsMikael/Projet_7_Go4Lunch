package com.mdasilva.go4lunch.data.model.restaurantDetails;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class RestaurantDetails implements Serializable {

    //Place detail
    @Json(name = "name")
    private String name;

    @Json(name = "vicinity")
    private String address;

    @Json(name = "formatted_phone_number")
    private String phoneNumber;

    @Json(name = "place_id")
    private String placeId;

    @Json(name = "website")
    private String website;

    @Json(name = "geometry")
    private Geometry geometry;

    @Json(name = "photos")
    private List<Photos> photos;

    @Json(name = "opening_hours")
    private OpeningHours openingHours;

    public RestaurantDetails(String name, String address, String phoneNumber, String placeId, String website, Geometry geometry, List<Photos> photos, OpeningHours openingHours) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.placeId = placeId;
        this.website = website;
        this.geometry = geometry;
        this.photos = photos;
        this.openingHours = openingHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", placeId='" + placeId + '\'' +
                ", website='" + website + '\'' +
                ", geometry=" + geometry +
                ", photos=" + photos +
                ", openingHours=" + openingHours +
                '}';
    }


    public static class Geometry implements Serializable {
        @Json(name = "location")
        private Location location;

        public Geometry(Location location) {
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    '}';
        }

        public static class Location implements Serializable{
            @Json(name = "lat")
            private String lat;

            @Json(name = "lng")
            private String lng;

            public Location(String lat, String lng) {
                this.lat = lat;
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            @Override
            public String toString() {
                return "Location{" +
                        "lat='" + lat + '\'' +
                        ", lng='" + lng + '\'' +
                        '}';
            }
        }
    }

    public static class Photos implements Serializable {

        @Json(name = "photo_reference")
        private String photoReference;

        public Photos(String photoReference) {
            this.photoReference = photoReference;
        }

        public String getPhotoReference() {
            return photoReference;
        }

        public void setPhotoReference(String photoReference) {
            this.photoReference = photoReference;
        }

        @Override
        public String toString() {
            return "Photos{" +
                    "photoReference='" + photoReference + '\'' +
                    '}';
        }
    }

    public static class OpeningHours implements Serializable {

        @Json(name = "open_now")
        private Boolean openNow;

        @Json(name = "periods")
        private List<Period> period;

        public OpeningHours(Boolean openNow, List<Period> period) {
            this.openNow = openNow;
            this.period = period;
        }

        public Boolean getOpenNow() {
            return openNow;
        }

        public void setOpenNow(Boolean openNow) {
            this.openNow = openNow;
        }

        public List<Period> getPeriod() {
            return period;
        }

        public void setPeriod(List<Period> period) {
            this.period = period;
        }

        @Override
        public String toString() {
            return "OpeningHours{" +
                    "openNow=" + openNow +
                    ", period=" + period +
                    '}';
        }

        public static class Period implements Serializable {

            @Json(name = "close")
            private Close close;

            @Json(name = "open")
            private Open open;

            public Period(Close close, Open open) {
                this.close = close;
                this.open = open;
            }

            public Close getClose() {
                return close;
            }

            public void setClose(Close close) {
                this.close = close;
            }

            public Open getOpen() {
                return open;
            }

            public void setOpen(Open open) {
                this.open = open;
            }

            @Override
            public String toString() {
                return "Period{" +
                        "close=" + close +
                        ", open=" + open +
                        '}';
            }

            public static class Close implements Serializable {
                @Json(name = "day")
                private String day;

                @Json(name = "time")
                private String time;

                public Close(String day, String time) {
                    this.day = day;
                    this.time = time;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                @Override
                public String toString() {
                    return "Open{" +
                            "day='" + day + '\'' +
                            ", time='" + time + '\'' +
                            '}';
                }

            }
            public static class Open implements Serializable {
                @Json(name = "day")
                private String day;

                @Json(name = "time")
                private String time;

                public Open(String day, String time) {
                    this.day = day;
                    this.time = time;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                @Override
                public String toString() {
                    return "Open{" +
                            "day='" + day + '\'' +
                            ", time='" + time + '\'' +
                            '}';
                }
            }


        }
    }


}
