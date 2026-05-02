package com.step.nova.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "ROLES", schema = "NS")
public class Role 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    public Role() {}

    public Role(Long roleId, String roleName, String description) 
    {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public Long getRoleId() { return roleId;    }
    public void setRoleId(Long roleId) {this.roleId = roleId;    }
    public String getRoleName() {return roleName;    }
    public void setRoleName(String roleName) {this.roleName = roleName;    }
    public String getDescription() {return description;    }
    public void setDescription(String description) {this.description = description;    }
    @Override
    public String toString() 
    {
        return "Role{" + "roleId=" + roleId + ", roleName='" + roleName + '\'' + ", description='" + description + '\'' +'}';
    }
}