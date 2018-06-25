<?xml version="1.0" ?>

<TestCase name="das" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="01/04/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="01/04/2018" host="HVDIVD18MIS1939" />
</meta>

<id>A8469E6AF14F11E78CDCC2DE20524153</id>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz03ODkyOTc0NjQ=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="Raw SOAP Test" log=""
          type="com.itko.lisa.ws.RawSOAPNode" 
          version="1" 
          uid="null" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<url>https://{{WSSERVER1}}:{{WSPORT1}}/Services/com/cingular/csi/ccr/InquireUnifiedCustomerLoginProfile.jws</url>
<action></action>
<soapRequest itko_enc="base64">PFNPQVAtRU5WOkVudmVsb3BlIHhtbG5zOlNPQVAtRU5WPSJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy9zb2FwL2VudmVsb3BlLyI+CiAgIDxTT0FQLUVOVjpIZWFkZXI+CiAgICAgIDxtZXM6TWVzc2FnZUhlYWRlciB4bWxuczptZXM9Imh0dHA6Ly9jc2kuY2luZ3VsYXIuY29tL0NTSS9OYW1lc3BhY2VzL09yZGVyQW5kU3Vic2NyaXB0aW9uTWFuYWdlbWVudE1vYmlsaXR5L0luZnJhc3RydWN0dXJlQ29tbW9uL1R5cGVzL1B1YmxpYy9NZXNzYWdlSGVhZGVyLnhzZCI+CiAgICAgICAgIDxtZXM6VHJhY2tpbmdNZXNzYWdlSGVhZGVyPgogICAgICAgICAgICA8bWVzOmluZnJhc3RydWN0dXJlVmVyc2lvbj44NjwvbWVzOmluZnJhc3RydWN0dXJlVmVyc2lvbj4KICAgICAgICAgICAgPG1lczphcHBsaWNhdGlvbk5hbWU+T3JkZXJBbmRTdWJzY3JpcHRpb25NYW5hZ2VtZW50TW9iaWxpdHk8L21lczphcHBsaWNhdGlvbk5hbWU+CiAgICAgICAgICAgIDxtZXM6dmVyc2lvbj4yMDY8L21lczp2ZXJzaW9uPgogICAgICAgICAgICA8bWVzOm1lc3NhZ2VJZD5PTEFNX1NMXzU3MDAwMjAwMF8xNDkzODk1MDAzMTcwMDAwNjRfcWt2MTlzbG0zXzI8L21lczptZXNzYWdlSWQ+CiAgICAgICAgICAgIDxtZXM6dGltZVRvTGl2ZT43ODAwMDwvbWVzOnRpbWVUb0xpdmU+CiAgICAgICAgICAgIDxtZXM6ZGF0ZVRpbWVTdGFtcD4yMDE3LTA1LTE5VDEzOjE0OjEwLjQxM1o8L21lczpkYXRlVGltZVN0YW1wPgogICAgICAgICAgICA8bWVzOnVuaXF1ZVRyYW5zYWN0aW9uSWQ+T0xBTV9TTF81NzAwMDIwMDBfMTQ5Mzg5NTAwMzE3MDAwMDY0X3FrdjE5c2xtM18yPC9tZXM6dW5pcXVlVHJhbnNhY3Rpb25JZD4KICAgICAgICAgPC9tZXM6VHJhY2tpbmdNZXNzYWdlSGVhZGVyPgogICAgICAgICA8bWVzOlNlY3VyaXR5TWVzc2FnZUhlYWRlcj4KICAgICAgICAgICAgPG1lczp1c2VyTmFtZT5teXdpcmVsZXNzPC9tZXM6dXNlck5hbWU+CiAgICAgICAgICAgIDxtZXM6dXNlclBhc3N3b3JkPm15d2lyZWxlc3MxPC9tZXM6dXNlclBhc3N3b3JkPgogICAgICAgICA8L21lczpTZWN1cml0eU1lc3NhZ2VIZWFkZXI+CiAgICAgICAgIDxtZXM6U2VxdWVuY2VNZXNzYWdlSGVhZGVyPgogICAgICAgICAgICA8bWVzOnNlcXVlbmNlTnVtYmVyPjE8L21lczpzZXF1ZW5jZU51bWJlcj4KICAgICAgICAgICAgPG1lczp0b3RhbEluU2VxdWVuY2U+MTwvbWVzOnRvdGFsSW5TZXF1ZW5jZT4KICAgICAgICAgPC9tZXM6U2VxdWVuY2VNZXNzYWdlSGVhZGVyPgogICAgICA8L21lczpNZXNzYWdlSGVhZGVyPgogICA8L1NPQVAtRU5WOkhlYWRlcj4KICAgICA8U09BUC1FTlY6Qm9keT4KICAgICAgPGlucTpJbnF1aXJlVW5pZmllZEN1c3RvbWVyTG9naW5Qcm9maWxlUmVxdWVzdCB4bWxuczppbnE9Imh0dHA6Ly9jc2kuY2luZ3VsYXIuY29tL0NTSS9OYW1lc3BhY2VzL0NvbnRhaW5lci9QdWJsaWMvSW5xdWlyZVVuaWZpZWRDdXN0b21lckxvZ2luUHJvZmlsZVJlcXVlc3QueHNkIj4KICAgICAgICAgPGlucTpBY2NvdW50QW5kU2VydmljZUlkZW50aWZpZXI+CiAgICAgICAgICAgIDxpbnE6dXZlcnNlQmlsbGluZ0FjY291bnROdW1iZXI+MzM2MDI4NTUxPC9pbnE6dXZlcnNlQmlsbGluZ0FjY291bnROdW1iZXI+CiAgICAgICAgIDwvaW5xOkFjY291bnRBbmRTZXJ2aWNlSWRlbnRpZmllcj4KICAgICAgICAgPGlucTpBY2NvdW50QW5kU2VydmljZU1vZGU+CiAgICAgICAgICAgIDxpbnE6VXNlckFjY291bnRTZXJ2aWNlRGV0YWlsPgogICAgICAgICAgICAgICA8aW5xOm1vZGU+VVNFUl9JREVOVElUWV9DT05UQUNUX0FDQ09VTlRfU0VSVklDRV9TVU1NQVJZX1JPTEVfREVUQUlMPC9pbnE6bW9kZT4KICAgICAgICAgICAgICAgPGlucTpBY2NvdW50U2VydmljZUNvZGU+CiAgICAgICAgICAgICAgICAgIDxpbnE6dXZlcnNlQWNjb3VudD5VVkVSU0VfQ09SRV9CSUxMRV9BRERSPC9pbnE6dXZlcnNlQWNjb3VudD4KICAgICAgICAgICAgICAgICAgPGlucTp1dmVyc2VBY2NvdW50PlVWRVJTRV9DT1JFX0NPTVBPTkVOVFM8L2lucTp1dmVyc2VBY2NvdW50PgogICAgICAgICAgICAgICAgICA8aW5xOnV2ZXJzZUFjY291bnQ+VVZFUlNFX0NPUkVfSVBUVl9HRU5SRV9QQUNLUzwvaW5xOnV2ZXJzZUFjY291bnQ+CiAgICAgICAgICAgICAgICAgIDxpbnE6dXZlcnNlQWNjb3VudD5VVkVSU0VfQ09SRV9JUFRWX1NUQjwvaW5xOnV2ZXJzZUFjY291bnQ+CiAgICAgICAgICAgICAgICAgIDxpbnE6dXZlcnNlQWNjb3VudD5VVkVSU0VfQ09SRV9OT1RJRlM8L2lucTp1dmVyc2VBY2NvdW50PgogICAgICAgICAgICAgICAgICA8aW5xOnV2ZXJzZUFjY291bnQ+VVZFUlNFX0NPUkVfUFJPRklMRTwvaW5xOnV2ZXJzZUFjY291bnQ+CiAgICAgICAgICAgICAgICAgIDxpbnE6dXZlcnNlQWNjb3VudD5VVkVSU0VfQ09SRV9VVkVSU0VfU0VSVklDRV9BRERSX01JU0M8L2lucTp1dmVyc2VBY2NvdW50PgogICAgICAgICAgICAgICAgICA8aW5xOnV2ZXJzZUFjY291bnQ+VVZFUlNFX0NPUkVfVk9JUF9UTjwvaW5xOnV2ZXJzZUFjY291bnQ+CiAgICAgICAgICAgICAgIDwvaW5xOkFjY291bnRTZXJ2aWNlQ29kZT4KICAgICAgICAgICAgPC9pbnE6VXNlckFjY291bnRTZXJ2aWNlRGV0YWlsPgogICAgICAgICA8L2lucTpBY2NvdW50QW5kU2VydmljZU1vZGU+CiAgICAgIDwvaW5xOklucXVpcmVVbmlmaWVkQ3VzdG9tZXJMb2dpblByb2ZpbGVSZXF1ZXN0PgogICA8L1NPQVAtRU5WOkJvZHk+CgoKPC9TT0FQLUVOVjpFbnZlbG9wZT4=</soapRequest>
<contentType>text/xml; charset=UTF-8</contentType>
<onError>abort</onError>
<discardResponse>false</discardResponse>
<customHTTPHeaderInfo>
</customHTTPHeaderInfo>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="A8469E6CF14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="A8469E6EF14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="abort" log="The test was aborted"
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="A8469E70F14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


</TestCase>
