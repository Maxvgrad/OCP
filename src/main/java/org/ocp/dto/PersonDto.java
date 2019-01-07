package org.ocp.dto;

public class PersonDto {
    private static int count = 0;
    private int id;
    private String interest;
    private String name;

    public PersonDto(String interest) {
        this.interest = interest;
        this.id = ++count;
    }

    private PersonDto() {
        this(null);
    }

    public String getInterest() {
        return interest;
    }

    private void setInterest(String interest) {
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return id+"";
    }

    public static Builder builder() {
        PersonDto person = new PersonDto();
        return person.new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder interest(String interest) {
            PersonDto.this.setInterest(interest);
            return this;
        }

        public Builder name(String name) {
            PersonDto.this.setName(name);
            return this;
        }

        public PersonDto build() {
            return PersonDto.this;
        }
    }
}