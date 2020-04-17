// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package org.ionic.photo.photoidentify.model;

import java.util.List;

public class PhotoRequest {

    private String imageValue;
    private String deviceId;

    public PhotoRequest() {
    }

    public String getImageValue() {
        return imageValue;
    }

    public void setImageValue(String imageValue) {
        this.imageValue = imageValue;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
