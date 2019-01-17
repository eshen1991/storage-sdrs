/*
 * Copyright 2019 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 * Any software provided by Google hereunder is distributed “AS IS”,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, and is not intended for production use.
 *
 */


package com.google.cloudy.retention.controller.mapper.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Handles Jackson JsonMappingExceptions with custom error messages.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMappingExceptionMapper extends JacksonExceptionMapper<JsonMappingException> {

  private static final Logger logger = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

  protected String createExceptionResponseMessage(JsonMappingException exception) {
    return String.format(
        "Invalid value for %s in line %d, column %d",
        exception.getPath().get(0).getFieldName(),
        exception.getLocation().getLineNr(),
        exception.getLocation().getColumnNr());
  }
}
