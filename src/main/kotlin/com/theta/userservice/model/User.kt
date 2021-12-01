package com.theta.userservice.model

import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.Type
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Email


@Entity
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    val userId: UUID = UUID.randomUUID()

    @Column(unique = true)
    @get:Email(message = "Not a valid email!")
    var email = ""

    @Column
    @get:NotBlank(message = "Password should not be blank!")
    var password = ""

    @Column(unique = true)
    @Length(min = 6)
    var displayName = ""

    @Column
    var profilePicture = "https://commons.wikimedia.org/wiki/File:Default_pfp.jpg"

    @Column
    var isEnabled = false;


    constructor(email:String, password:String, displayName: String, isEnabled: Boolean) {
        this.email = email
        this.password = password
        this.displayName = displayName
        this.isEnabled = isEnabled
    } // getters and setters
    constructor(){

    }
}
