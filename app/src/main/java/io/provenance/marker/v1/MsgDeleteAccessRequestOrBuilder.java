// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/marker/v1/tx.proto

package io.provenance.marker.v1;

public interface MsgDeleteAccessRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.marker.v1.MsgDeleteAccessRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string denom = 1;</code>
   * @return The denom.
   */
  java.lang.String getDenom();
  /**
   * <code>string denom = 1;</code>
   * @return The bytes for denom.
   */
  com.google.protobuf.ByteString
      getDenomBytes();

  /**
   * <code>string administrator = 2;</code>
   * @return The administrator.
   */
  java.lang.String getAdministrator();
  /**
   * <code>string administrator = 2;</code>
   * @return The bytes for administrator.
   */
  com.google.protobuf.ByteString
      getAdministratorBytes();

  /**
   * <code>string removed_address = 3;</code>
   * @return The removedAddress.
   */
  java.lang.String getRemovedAddress();
  /**
   * <code>string removed_address = 3;</code>
   * @return The bytes for removedAddress.
   */
  com.google.protobuf.ByteString
      getRemovedAddressBytes();
}