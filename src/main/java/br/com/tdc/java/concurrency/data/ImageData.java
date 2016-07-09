package br.com.tdc.java.concurrency.data;

/**
 * Image content
 */
public class ImageData {

    private String data;
    private ImageSize size;

    public ImageData(String data, ImageSize size) {
        this.data = data;
        this.size = size;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image {");
        sb.append("'").append(data).append('\'');
        sb.append(", ").append(size);
        sb.append('}');
        return sb.toString();
    }

    public ImageSize getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageData imageData = (ImageData) o;

        if (getData() != null ? !getData().equals(imageData.getData()) : imageData.getData() != null) return false;
        return getSize() == imageData.getSize();

    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        return result;
    }
}
