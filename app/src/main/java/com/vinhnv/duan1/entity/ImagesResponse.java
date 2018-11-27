package com.vinhnv.duan1.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImagesResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("response_time")
    private String response_time;
    @SerializedName("items")
    private ArrayList<ItemsBean> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResponse_time() {
        return response_time;
    }

    public void setResponse_time(String response_time) {
        this.response_time = response_time;
    }

    public ArrayList<ItemsBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        @SerializedName("author")
        private String author;
        @SerializedName("category_id")
        private int category_id;
        @SerializedName("content_type")
        private String content_type;
        @SerializedName("description")
        private String description;
        @SerializedName("downloads")
        private int downloads;
        @SerializedName("id")
        private int id;
        @SerializedName("license")
        private String license;
        @SerializedName("rating")
        private int rating;
        @SerializedName("source_link")
        private String source_link;
        @SerializedName("uploaded_at")
        private String uploaded_at;
        @SerializedName("variations")
        private VariationsBean variations;
        @SerializedName("tags")
        private List<String> tags;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSource_link() {
            return source_link;
        }

        public void setSource_link(String source_link) {
            this.source_link = source_link;
        }

        public String getUploaded_at() {
            return uploaded_at;
        }

        public void setUploaded_at(String uploaded_at) {
            this.uploaded_at = uploaded_at;
        }

        public VariationsBean getVariations() {
            return variations;
        }

        public void setVariations(VariationsBean variations) {
            this.variations = variations;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class VariationsBean {
            @SerializedName("adapted")
            private AdaptedBean adapted;
            @SerializedName("adapted_landscape")
            private AdaptedLandscapeBean adapted_landscape;
            @SerializedName("original")
            private OriginalBean original;
            @SerializedName("preview_small")
            private PreviewSmallBean preview_small;

            public AdaptedBean getAdapted() {
                return adapted;
            }

            public void setAdapted(AdaptedBean adapted) {
                this.adapted = adapted;
            }

            public AdaptedLandscapeBean getAdapted_landscape() {
                return adapted_landscape;
            }

            public void setAdapted_landscape(AdaptedLandscapeBean adapted_landscape) {
                this.adapted_landscape = adapted_landscape;
            }

            public OriginalBean getOriginal() {
                return original;
            }

            public void setOriginal(OriginalBean original) {
                this.original = original;
            }

            public PreviewSmallBean getPreview_small() {
                return preview_small;
            }

            public void setPreview_small(PreviewSmallBean preview_small) {
                this.preview_small = preview_small;
            }

            public static class AdaptedBean {
                @SerializedName("resolution")
                private ResolutionBean resolution;
                @SerializedName("size")
                private int size;
                @SerializedName("url")
                private String url;

                public ResolutionBean getResolution() {
                    return resolution;
                }

                public void setResolution(ResolutionBean resolution) {
                    this.resolution = resolution;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public static class ResolutionBean {
                    @SerializedName("height")
                    private int height;
                    @SerializedName("width")
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }

            public static class AdaptedLandscapeBean {
                @SerializedName("resolution")
                private ResolutionBeanX resolution;
                @SerializedName("size")
                private int size;
                @SerializedName("url")
                private String url;

                public ResolutionBeanX getResolution() {
                    return resolution;
                }

                public void setResolution(ResolutionBeanX resolution) {
                    this.resolution = resolution;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public static class ResolutionBeanX {
                    @SerializedName("height")
                    private int height;
                    @SerializedName("width")
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }

            public static class OriginalBean {
                @SerializedName("resolution")
                private ResolutionBeanXX resolution;
                @SerializedName("size")
                private int size;
                @SerializedName("url")
                private String url;

                public ResolutionBeanXX getResolution() {
                    return resolution;
                }

                public void setResolution(ResolutionBeanXX resolution) {
                    this.resolution = resolution;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public static class ResolutionBeanXX {
                    @SerializedName("height")
                    private int height;
                    @SerializedName("width")
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }

            public static class PreviewSmallBean {

                @SerializedName("resolution")
                private ResolutionBeanXXX resolution;
                @SerializedName("size")
                private int size;
                @SerializedName("url")
                private String url;

                public ResolutionBeanXXX getResolution() {
                    return resolution;
                }

                public void setResolution(ResolutionBeanXXX resolution) {
                    this.resolution = resolution;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public static class ResolutionBeanXXX {
                    @SerializedName("height")
                    private int height;
                    @SerializedName("width")
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }
    }
}
