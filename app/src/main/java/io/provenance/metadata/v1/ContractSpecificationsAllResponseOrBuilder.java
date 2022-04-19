// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/metadata/v1/query.proto

package io.provenance.metadata.v1;

public interface ContractSpecificationsAllResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.metadata.v1.ContractSpecificationsAllResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * contract_specifications are the wrapped contract specifications.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ContractSpecificationWrapper contract_specifications = 1 [(.gogoproto.moretags) = "yaml:&#92;"contract_specifications&#92;""];</code>
   */
  java.util.List<io.provenance.metadata.v1.ContractSpecificationWrapper> 
      getContractSpecificationsList();
  /**
   * <pre>
   * contract_specifications are the wrapped contract specifications.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ContractSpecificationWrapper contract_specifications = 1 [(.gogoproto.moretags) = "yaml:&#92;"contract_specifications&#92;""];</code>
   */
  io.provenance.metadata.v1.ContractSpecificationWrapper getContractSpecifications(int index);
  /**
   * <pre>
   * contract_specifications are the wrapped contract specifications.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ContractSpecificationWrapper contract_specifications = 1 [(.gogoproto.moretags) = "yaml:&#92;"contract_specifications&#92;""];</code>
   */
  int getContractSpecificationsCount();
  /**
   * <pre>
   * contract_specifications are the wrapped contract specifications.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ContractSpecificationWrapper contract_specifications = 1 [(.gogoproto.moretags) = "yaml:&#92;"contract_specifications&#92;""];</code>
   */
  java.util.List<? extends io.provenance.metadata.v1.ContractSpecificationWrapperOrBuilder> 
      getContractSpecificationsOrBuilderList();
  /**
   * <pre>
   * contract_specifications are the wrapped contract specifications.
   * </pre>
   *
   * <code>repeated .provenance.metadata.v1.ContractSpecificationWrapper contract_specifications = 1 [(.gogoproto.moretags) = "yaml:&#92;"contract_specifications&#92;""];</code>
   */
  io.provenance.metadata.v1.ContractSpecificationWrapperOrBuilder getContractSpecificationsOrBuilder(
      int index);

  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ContractSpecificationsAllRequest request = 98;</code>
   * @return Whether the request field is set.
   */
  boolean hasRequest();
  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ContractSpecificationsAllRequest request = 98;</code>
   * @return The request.
   */
  io.provenance.metadata.v1.ContractSpecificationsAllRequest getRequest();
  /**
   * <pre>
   * request is a copy of the request that generated these results.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ContractSpecificationsAllRequest request = 98;</code>
   */
  io.provenance.metadata.v1.ContractSpecificationsAllRequestOrBuilder getRequestOrBuilder();

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