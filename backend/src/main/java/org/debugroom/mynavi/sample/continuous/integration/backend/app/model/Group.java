package org.debugroom.mynavi.sample.continuous.integration.backend.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Group implements Serializable {

    private long groupId;
    private String groupName;

}
