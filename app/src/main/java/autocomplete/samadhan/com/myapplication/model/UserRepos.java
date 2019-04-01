package autocomplete.samadhan.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class UserRepos {

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("updated_at")
    String updatyedAt;

    @SerializedName("stargazers_count")
    String starCount;

    @SerializedName("forks")
    String forks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatyedAt() {
        return updatyedAt;
    }

    public void setUpdatyedAt(String updatyedAt) {
        this.updatyedAt = updatyedAt;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }
}
