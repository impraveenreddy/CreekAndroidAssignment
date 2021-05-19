package com.sample.creekandroidassignment.model;

import java.util.List;

public class ResponseData {

    private String total_count;

    private List<Icons> icons;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<Icons> getIcons() {
        return icons;
    }

    public void setIcons(List<Icons> icons) {
        this.icons = icons;
    }

    public static class Icons {

        private Vector_sizes[] vector_sizes;

        private String icon_id;

        private String type;

        private String[] tags;

        private String is_icon_glyph;

        private String is_premium;

        private List<Raster_sizes> raster_sizes;

        private String[] styles;

        private List<Containers> containers;

        private String[] categories;

        private String published_at;

        private List<Prices> prices;

        private String is_purchased;

        public Vector_sizes[] getVector_sizes() {
            return vector_sizes;
        }

        public void setVector_sizes(Vector_sizes[] vector_sizes) {
            this.vector_sizes = vector_sizes;
        }

        public String getIcon_id() {
            return icon_id;
        }

        public void setIcon_id(String icon_id) {
            this.icon_id = icon_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public String getIs_icon_glyph() {
            return is_icon_glyph;
        }

        public void setIs_icon_glyph(String is_icon_glyph) {
            this.is_icon_glyph = is_icon_glyph;
        }

        public String getIs_premium() {
            return is_premium;
        }

        public void setIs_premium(String is_premium) {
            this.is_premium = is_premium;
        }

        public List<Raster_sizes> getRaster_sizes() {
            return raster_sizes;
        }

        public void setRaster_sizes(List<Raster_sizes> raster_sizes) {
            this.raster_sizes = raster_sizes;
        }

        public String[] getStyles() {
            return styles;
        }

        public void setStyles(String[] styles) {
            this.styles = styles;
        }

        public List<Containers> getContainers() {
            return containers;
        }

        public void setContainers(List<Containers> containers) {
            this.containers = containers;
        }

        public String[] getCategories() {
            return categories;
        }

        public void setCategories(String[] categories) {
            this.categories = categories;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public List<Prices> getPrices() {
            return prices;
        }

        public void setPrices(List<Prices> prices) {
            this.prices = prices;
        }

        public String getIs_purchased() {
            return is_purchased;
        }

        public void setIs_purchased(String is_purchased) {
            this.is_purchased = is_purchased;
        }
    }

    public class Vector_sizes {
        private String size_height;

        private List<Formats> formats;

        private String size;

        private String size_width;

        private String[][] target_sizes;

        public String getSize_height() {
            return size_height;
        }

        public void setSize_height(String size_height) {
            this.size_height = size_height;
        }

        public List<Formats> getFormats() {
            return formats;
        }

        public void setFormats(List<Formats> formats) {
            this.formats = formats;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSize_width() {
            return size_width;
        }

        public void setSize_width(String size_width) {
            this.size_width = size_width;
        }

        public String[][] getTarget_sizes() {
            return target_sizes;
        }

        public void setTarget_sizes(String[][] target_sizes) {
            this.target_sizes = target_sizes;
        }

    }

    public class Raster_sizes {
        private String size_height;

        private List<Formats> formats;

        private String size;

        private String size_width;

        public String getSize_height() {
            return size_height;
        }

        public void setSize_height(String size_height) {
            this.size_height = size_height;
        }

        public List<Formats> getFormats() {
            return formats;
        }

        public void setFormats(List<Formats> formats) {
            this.formats = formats;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSize_width() {
            return size_width;
        }

        public void setSize_width(String size_width) {
            this.size_width = size_width;
        }

    }

    public class Formats {
        private String preview_url;

        private String format;

        private String download_url;

        public String getPreview_url() {
            return preview_url;
        }

        public void setPreview_url(String preview_url) {
            this.preview_url = preview_url;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

    }

    public class Containers {
        private String format;

        private String download_url;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

    }

    public class Prices {
        private License license;

        private String price;

        private String currency;

        public License getLicense() {
            return license;
        }

        public void setLicense(License license) {
            this.license = license;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

    }

    public class License {
        private String license_id;

        private String scope;

        private String name;

        private String url;

        public String getLicense_id() {
            return license_id;
        }

        public void setLicense_id(String license_id) {
            this.license_id = license_id;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}