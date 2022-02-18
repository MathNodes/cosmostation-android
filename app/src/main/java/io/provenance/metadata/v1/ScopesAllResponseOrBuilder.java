// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/metadata/v1/query.proto

package io.provenance.metadata.v1;

public interface ScopesAllResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.metadata.v1.ScopesAllResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * scopes are the wrapped scopes.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ScopeWrapper scopes = 1;</code>
   */
  java.util.List<io.provenance.metadata.v1.ScopeWrapper> 
      getScopesList();
  /**
   * <pre>
   * scopes are the wrapped scopes.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ScopeWrapper scopes = 1;</code>
   */
  io.provenance.metadata.v1.ScopeWrapper getScopes(int index);
  /**
   * <pre>
   * scopes are the wrapped scopes.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ScopeWrapper scopes = 1;</code>
   */
  int getScopesCount();
  /**
   * <pre>
   * scopes are the wrapped scopes.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ScopeWrapper scopes = 1;</code>
   */
  java.util.List<? extends io.provenance.metadata.v1.ScopeWrapperOrBuilder> 
      getScopesOrBuilderList();
  /**
   * <pre>
   * scopes are the wrapped scopes.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ScopeWrapper scopes = 1;</code>
   */
  io.provenance.metadata.v1.ScopeWrapperOrBuilder getScopesOrBuilder(
      int index);

  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopesAllRequest request = 98;</code>
   * @return Whether the request field is set.
   */
  boolean hasRequest();
  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopesAllRequest request = 98;</code>
   * @return The request.
   */
  io.provenance.metadata.v1.ScopesAllRequest getRequest();
  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopesAllRequest request = 98;</code>
   */
  io.provenance.metadata.v1.ScopesAllRequestOrBuilder getRequestOrBuilder();

  /**
   * <pre>
   * pagination provides the pagination information of this response.
   * </pre>
   *
   * <code>.cosmos.base.query.v1beta1.PageResponse pagination = 99;</code>
   * @return Whether the pagination field is set.
   */
  boolean hasPagination();
  /**
   * <pre>
   * pagination provides the pagination information of this response.
   * </pre>
   *
   * <code>.cosmos.base.query.v1beta1.PageResponse pagination = 99;</code>
   * @return The pagination.
   */
  cosmos.base.query.v1beta1.Pagination.PageResponse getPagination();
  /**
   * <pre>
   * pagination provides the pagination information of this response.
   * </pre>
   *
   * <code>.cosmos.base.query.v1beta1.PageResponse pagination = 99;</code>
   */
  cosmos.base.query.v1beta1.Pagination.PageResponseOrBuilder getPaginationOrBuilder();
}
